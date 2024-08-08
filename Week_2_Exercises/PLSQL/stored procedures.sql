--question1

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
    CURSOR c_savings_accounts IS
        SELECT AccountID, Balance
        FROM Accounts
        WHERE AccountType = 'Savings'
        FOR UPDATE;
BEGIN
    FOR account_rec IN c_savings_accounts LOOP
        -- Calculate interest
        UPDATE Accounts
        SET Balance = Balance + (Balance * 0.01),
            LastModified = SYSDATE
        WHERE AccountID = account_rec.AccountID;
    END LOOP;
    
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Monthly interest processed for all savings accounts.');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END ProcessMonthlyInterest;
/
--question2
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department VARCHAR2,
    p_bonus_percentage NUMBER
)
IS
BEGIN
    -- Update the salary of employees in the given department
    UPDATE Employees
    SET Salary = Salary * (1 + p_bonus_percentage / 100)
    WHERE Department = p_department;

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Bonus applied to employees in department ' || p_department || '.');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END UpdateEmployeeBonus;
/
--question3
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id NUMBER,
    p_to_account_id NUMBER,
    p_amount NUMBER
)
IS
    v_balance_from NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    -- Get balance of the source account
    SELECT Balance INTO v_balance_from 
    FROM Accounts 
    WHERE AccountID = p_from_account_id 
    FOR UPDATE;

    -- Check if there are sufficient funds
    IF v_balance_from < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Deduct amount from source account
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account_id;

    -- Add amount to destination account
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account_id;

    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully.');
    
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in the source account.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END TransferFunds;
/
