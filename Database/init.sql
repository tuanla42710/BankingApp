create database bank;

use bank;

create table bank_account (
	account_number varchar(20),
    customer_id varchar(20),
    account_type varchar(20),
    account_status varchar(10),
    balance double,
    last_update varchar(20),
    primary key(account_number)
);

create table bank_user (
	user_id varchar(20),
    customer_id varchar(20),
    user_name varchar(20),
    user_pass varchar(20),
    last_update varchar(20),
    primary key(user_id)
);

create table bank.users (
    user_name varchar(20),
    user_pass varchar(256),
    primary key(user_name)
);


CREATE TABLE bank_transaction (
    trans_id INT auto_increment PRIMARY KEY,
    hostref varchar(255),
    account_number VARCHAR(255),
    customer_id VARCHAR(255),
    amount DOUBLE,
    trx_context VARCHAR(255),
    ict VARCHAR(255),
    category VARCHAR(255),
    ofs_account VARCHAR(255),
    ofs_customer VARCHAR(255),
    last_update VARCHAR(255)
);


-- Insert data into bank_account
INSERT INTO bank_account (account_number, customer_id, account_type, account_status, balance, last_update) VALUES
('123456789', '1000001', 'SAVINGS', 'active', 1500.50, '2025-04-10'),
('987654321', '1000002', 'CHECKING', 'inactive', 250.00, '2025-04-11'),
('456789123', '1000003', 'SAVINGS', 'active', 3300.75, '2025-04-12');

-- Insert data into bank_user
INSERT INTO bank_user (user_id, customer_id, user_name, user_pass, last_update) VALUES
('USR001', '1000001', 'john_doe', 'pass123', '2025-04-10'),
('USR002', '1000002', 'jane_smith', 'qwerty', '2025-04-11'),
('USR003', '1000003', 'alice_bob', 'abc123', '2025-04-12');



CREATE TABLE transaction_category (
	pid int auto_increment PRIMARY KEY,
    trans_id VARCHAR(255) ,
    account_number VARCHAR(255),
    customer_id VARCHAR(255),
    category varchar(50),
    amount DOUBLE,
    version int,
    exec_date VARCHAR(255)
);

DROP TRIGGER IF EXISTS before_insert_transaction;

DELIMITER //

CREATE TRIGGER before_insert_transaction
BEFORE INSERT ON transaction_category
FOR EACH ROW
BEGIN
    DECLARE max_version INT;

    SELECT IFNULL(MAX(version), 0) + 1 INTO max_version
    FROM transaction_category
    WHERE trans_id = NEW.trans_id;

    SET NEW.version = max_version;
END;
//

DELIMITER ;


INSERT INTO transaction_category (
    trans_id, account_number, customer_id, category, amount, exec_date
) VALUES
('T001', 'AC001', 'C001', 'Salary', 1000.00, '2025-04-15');


create table categories (
	name_vi varchar(50),
    name_en varchar(50),
    income_or_expense varchar(50)
);



INSERT INTO categories (name_vi, name_en, income_or_expense) VALUES
('Lương', 'Salary', 'income'),
('Thưởng', 'Bonus', 'income'),
('Kinh doanh', 'Business', 'income'),
('Ăn uống', 'Food & Drink', 'expense'),
('Đi lại', 'Transportation', 'expense'),
('Giải trí', 'Entertainment', 'expense'),
('Hóa đơn', 'Bills', 'expense'),
('Mua sắm', 'Shopping', 'expense'),
('Y tế', 'Healthcare', 'expense'),
('Giáo dục', 'Education', 'expense');






INSERT INTO bank_transaction (
    hostref,
    account_number,
    customer_id,
    amount,
    trx_context,
    ict,
    category,
    ofs_account,
    ofs_customer,
    last_update
)
VALUES
('HR001', '123456789', '1000001', 1500.00, 'Monthly salary', 'ICT001', 'Salary', 'OFS001', 'OFS_CUST001', '2025-04-01'),
('HR002', '123456789', '1000001', 300.00, 'Quarterly bonus', 'ICT002', 'Bonus', 'OFS002', 'OFS_CUST002', '2025-04-01'),
('HR003', '123456789', '1000001', 1200.00, 'Business profit', 'ICT003', 'Business', 'OFS003', 'OFS_CUST003', '2025-04-01'),
('HR004', '987654321', '1000002', -50.00, 'Lunch with colleagues', 'ICT004', 'Food & Drink', 'OFS004', 'OFS_CUST004', '2025-04-01'),
('HR005', '987654321', '1000002', -20.00, 'Bus fare', 'ICT005', 'Transportation', 'OFS005', 'OFS_CUST005', '2025-04-01'),
('HR006', '987654321', '1000002', -100.00, 'Movie tickets', 'ICT006', 'Entertainment', 'OFS006', 'OFS_CUST006', '2025-04-01'),
('HR007', '456789123', '1000003', -200.00, 'Electricity bill', 'ICT007', 'Bills', 'OFS007', 'OFS_CUST007', '2025-04-01'),
('HR008', '456789123', '1000003', -300.00, 'Clothes shopping', 'ICT008', 'Shopping', 'OFS008', 'OFS_CUST008', '2025-04-01'),
('HR009', '456789123', '1000003', -150.00, 'Doctor visit', 'ICT009', 'Healthcare', 'OFS009', 'OFS_CUST009', '2025-04-01'),
('HR010', '456789123', '1000003', -500.00, 'Tuition fees', 'ICT010', 'Education', 'OFS010', 'OFS_CUST010', '2025-04-01');

INSERT INTO bank_transaction (
    hostref, account_number, customer_id, amount, trx_context, ict, category, ofs_account, ofs_customer, last_update
)
VALUES
('HR0011', '456789123', '1000003', 2500.00, 'Year-end bonus', 'ICT005', 'Bonus', 'OFS005', 'OFS_CUST005', '2025-04-05'),
('HR0012', '456789123', '1000003', -300.00, 'Hospital checkup', 'ICT006', 'Healthcare', 'OFS006', 'OFS_CUST006', '2025-04-06');