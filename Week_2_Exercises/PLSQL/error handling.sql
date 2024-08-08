--question1
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account_id NUMBER,
    p_to_account_id NUMBER,
    p_amount NUMBER
)
IS
    v_balance_from NUMBER;
    v_balance_to NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    -- Get balance of the from account
    SELECT Balance INTO v_balance_from FROM Accounts WHERE AccountID = p_from_account_id FOR UPDATE;
    
    -- Check if there are sufficient funds
    IF v_balance_from < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Perform the transfer
    UPDATE Accounts
    SET Balance = Balance - p_amount, LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount, LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Transfer successful.');
    
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in the from account.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END SafeTransferFunds;
/

--question2
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account_id NUMBER,
    p_to_account_id NUMBER,
    p_amount NUMBER
)
IS
    v_balance_from NUMBER;
    v_balance_to NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    -- Get balance of the from account
    SELECT Balance INTO v_balance_from FROM Accounts WHERE AccountID = p_from_account_id FOR UPDATE;
    
    -- Check if there are sufficient funds
    IF v_balance_from < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Perform the transfer
    UPDATE Accounts
    SET Balance = Balance - p_amount, LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount, LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Transfer successful.');
    
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in the from account.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END SafeTransferFunds;
/
BEGIN
    SafeTransferFunds(1, 2, 500);
END;
/

--question3
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
)
IS
    customer_exists EXCEPTION;
BEGIN
    -- Insert new customer
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
    
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' already exists.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END AddNewCustomer;
/
