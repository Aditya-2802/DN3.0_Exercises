--question1
DECLARE
    v_age NUMBER;
    CURSOR c_customers IS
        SELECT c.CustomerID, c.DOB, l.InterestRate 
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID;
BEGIN
    FOR customer_rec IN c_customers LOOP
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, customer_rec.DOB) / 12);
        
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = customer_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

--question2
ALTER TABLE Customers ADD IsVIP VARCHAR2(3) DEFAULT 'NO';

BEGIN
    FOR rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF rec.Balance > 1000 THEN
            UPDATE Customers
            SET IsVIP = 'YES'
            WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

select * from Customers;
select * from Accounts;

--question3

DECLARE
    v_customer_name VARCHAR2(100);
    CURSOR c_loans IS
        SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name 
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR loan_rec IN c_loans LOOP
        v_customer_name := loan_rec.Name;
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || v_customer_name || 
                             ', your loan with LoanID ' || loan_rec.LoanID || 
                             ' is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD') || 
                             '. Please ensure timely payment.');
    END LOOP;
END;
/
