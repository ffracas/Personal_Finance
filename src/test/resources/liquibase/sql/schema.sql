-- Table: Users
CREATE TABLE Users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: FinancialGoals
CREATE TABLE FinancialGoals (
                                id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                user_id UUID UNIQUE NOT NULL,
                                emergency_fund_target DECIMAL(15, 2),
                                investment_target DECIMAL(15, 2),
                                bonds_target DECIMAL(15, 2),
                                loans_target DECIMAL(15, 2),
                                budget_allocation JSONB,
                                emergency_months INT,
                                FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Table: Transactions
CREATE TABLE Transactions (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              user_id UUID NOT NULL,
                              amount DECIMAL(15, 2) NOT NULL,
                              category VARCHAR(100),
                              sub_category VARCHAR(100),
                              type VARCHAR(10) CHECK (type IN ('income', 'expense')),
                              transaction_date DATE NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Table: RecurringExpenses
CREATE TABLE RecurringExpenses (
                                   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                   user_id UUID NOT NULL,
                                   amount DECIMAL(15, 2) NOT NULL,
                                   category VARCHAR(100),
                                   frequency VARCHAR(20),
                                   start_date DATE NOT NULL,
                                   end_date DATE,
                                   FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Table: Investments
CREATE TABLE Investments (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             user_id UUID NOT NULL,
                             name VARCHAR(100),
                             code VARCHAR(50),
                             shares_owned DECIMAL(15, 4),
                             unit_price DECIMAL(15, 4),
                             investment_date DATE NOT NULL,
                             current_value DECIMAL(15, 2),
                             FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Table: DepositAccounts
CREATE TABLE DepositAccounts (
                                 id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                 user_id UUID NOT NULL,
                                 invested_amount DECIMAL(15, 2) NOT NULL,
                                 annual_rate DECIMAL(5, 2),
                                 start_date DATE NOT NULL,
                                 end_date DATE NOT NULL,
                                 FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- Table: Bonds
CREATE TABLE Bonds (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       user_id UUID NOT NULL,
                       name VARCHAR(100),
                       code VARCHAR(50),
                       invested_amount DECIMAL(15, 2) NOT NULL,
                       annual_rate DECIMAL(5, 2),
                       maturity_date DATE NOT NULL,
                       coupon_type VARCHAR(50),
                       current_value DECIMAL(15, 2),
                       FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

-- View: WealthOverview
CREATE VIEW WealthOverview AS
SELECT
    u.id AS user_id,
    COALESCE(SUM(i.current_value), 0) +
    COALESCE(SUM(b.current_value), 0) +
    COALESCE(SUM(da.invested_amount), 0) AS total_wealth,
    COALESCE(SUM(CASE WHEN t.type = 'income' THEN t.amount ELSE 0 END), 0) -
    COALESCE(SUM(CASE WHEN t.type = 'expense' THEN t.amount ELSE 0 END), 0) AS cash_flow,
    fg.emergency_fund_target,
    fg.investment_target,
    fg.bonds_target,
    fg.loans_target
FROM
    Users u
        LEFT JOIN FinancialGoals fg ON u.id = fg.user_id
        LEFT JOIN Investments i ON u.id = i.user_id
        LEFT JOIN Bonds b ON u.id = b.user_id
        LEFT JOIN DepositAccounts da ON u.id = da.user_id
        LEFT JOIN Transactions t ON u.id = t.user_id
GROUP BY
    u.id, fg.emergency_fund_target, fg.investment_target, fg.bonds_target, fg.loans_target;
