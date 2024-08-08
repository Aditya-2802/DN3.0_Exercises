--question1
CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE)
RETURN NUMBER
IS
    v_age NUMBER;
BEGIN
    -- Calculate age in years
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END CalculateAge;
/

--question2
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_loan_duration_years NUMBER
)
RETURN NUMBER
IS
    v_monthly_installment NUMBER;
    v_monthly_interest_rate NUMBER;
    v_total_months NUMBER;
BEGIN
    -- Convert annual interest rate to monthly
    v_monthly_interest_rate := p_interest_rate / 12 / 100;

    -- Calculate the total number of months
    v_total_months := p_loan_duration_years * 12;

    -- Calculate monthly installment using the formula
    v_monthly_installment := p_loan_amount * v_monthly_interest_rate /
        (1 - POWER((1 + v_monthly_interest_rate), -v_total_months));

    RETURN v_monthly_installment;
END CalculateMonthlyInstallment;
/

--question3
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount NUMBER
)
RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    -- Retrieve the balance of the specified account
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    -- Check if the balance is sufficient
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN OTHERS THEN
        RETURN FALSE;
END HasSufficientBalance;
/
