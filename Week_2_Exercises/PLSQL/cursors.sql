--question1
DECLARE
    CURSOR c_monthly_transactions IS
        SELECT t.TransactionID, t.AccountID, t.TransactionDate, t.Amount, t.TransactionType, c.Name
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE TO_CHAR(t.TransactionDate, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM');

    v_transaction_id Transactions.TransactionID%TYPE;
    v_account_id Accounts.AccountID%TYPE;
    v_transaction_date Transactions.TransactionDate%TYPE;
    v_amount Transactions.Amount%TYPE;
    v_transaction_type Transactions.TransactionType%TYPE;
    v_customer_name Customers.Name%TYPE;
BEGIN
    OPEN c_monthly_transactions;
    LOOP
        FETCH c_monthly_transactions INTO v_transaction_id, v_account_id, v_transaction_date, v_amount, v_transaction_type, v_customer_name;
        EXIT WHEN c_monthly_transactions%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Customer: ' || v_customer_name);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || TO_CHAR(v_transaction_date, 'YYYY-MM-DD'));
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transaction_type);
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('-----------------------------------');
    END LOOP;

    CLOSE c_monthly_transactions;
END;
/

--question2
DECLARE
    CURSOR c_accounts IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;

    v_account_id Accounts.AccountID%TYPE;
    v_balance Accounts.Balance%TYPE;
    v_annual_fee NUMBER := 50; -- Example annual fee
BEGIN
    OPEN c_accounts;
    LOOP
        FETCH c_accounts INTO v_account_id, v_balance;
        EXIT WHEN c_accounts%NOTFOUND;

        -- Deduct annual fee
        UPDATE Accounts
        SET Balance = Balance - v_annual_fee
        WHERE AccountID = v_account_id;

        DBMS_OUTPUT.PUT_LINE('Applied annual fee to Account ID: ' || v_account_id || ', New Balance: ' || (v_balance - v_annual_fee));
    END LOOP;

    COMMIT;
    CLOSE c_accounts;
END;
/

--question3
DECLARE
    CURSOR c_loans IS
        SELECT LoanID, InterestRate
        FROM Loans
        FOR UPDATE;

    v_loan_id Loans.LoanID%TYPE;
    v_interest_rate Loans.InterestRate%TYPE;
    v_new_interest_rate NUMBER;
BEGIN
    OPEN c_loans;
    LOOP
        FETCH c_loans INTO v_loan_id, v_interest_rate;
        EXIT WHEN c_loans%NOTFOUND;

        -- Apply new interest rate policy (e.g., decrease by 0.5%)
        v_new_interest_rate := v_interest_rate - 0.5;

        UPDATE Loans
        SET InterestRate = v_new_interest_rate
        WHERE LoanID = v_loan_id;

        DBMS_OUTPUT.PUT_LINE('Updated Loan ID: ' || v_loan_id || ', New Interest Rate: ' || v_new_interest_rate);
    END LOOP;

    COMMIT;
    CLOSE c_loans;
END;
/
