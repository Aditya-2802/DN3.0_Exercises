--question1
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    -- Set the LastModified column to the current date
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

--question2
CREATE SEQUENCE AuditLog_seq
START WITH 1
INCREMENT BY 1;
CREATE SEQUENCE AuditLog_seq
START WITH 1
INCREMENT BY 1;
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    -- Insert a record into the AuditLog table
    INSERT INTO AuditLog (LogID, TransactionID, AccountID, TransactionDate, Amount, TransactionType, LogTimestamp)
    VALUES (AuditLog_seq.NEXTVAL, :NEW.TransactionID, :NEW.AccountID, :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END LogTransaction;
/


--question3
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
    insufficient_funds EXCEPTION;
    invalid_deposit EXCEPTION;
BEGIN
    -- Check if the transaction is a withdrawal
    IF :NEW.TransactionType = 'Withdrawal' THEN
        -- Get the current balance of the account
        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID
        FOR UPDATE;

        -- Ensure the balance is sufficient for the withdrawal
        IF v_balance < :NEW.Amount THEN
            RAISE insufficient_funds;
        END IF;

    -- Check if the transaction is a deposit
    ELSIF :NEW.TransactionType = 'Deposit' THEN
        -- Ensure the deposit amount is positive
        IF :NEW.Amount <= 0 THEN
            RAISE invalid_deposit;
        END IF;
    END IF;

EXCEPTION
    WHEN insufficient_funds THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds for this withdrawal.');

    WHEN invalid_deposit THEN
        RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
END CheckTransactionRules;
/
