BEGIN TRANSACTION;
CREATE TABLE member_discounts (member_id INTEGER PRIMARY KEY AUTOINCREMENT, discounts NUMERIC);
CREATE TABLE member_iou (member_id INTEGER PRIMARY KEY AUTOINCREMENT, iou_amount NUMERIC);
CREATE TABLE members (id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT, email_address TEXT, expiration_date NUMERIC, membership_type NUMERIC, year_in_school NUMERIC, is_active NUMERIC);
CREATE TABLE schedule (id INTEGER PRIMARY KEY AUTOINCREMENT, day NUMERIC, hour NUMERIC, coordinator_id NUMERIC, number_of_volunteers NUMERIC);
CREATE TABLE shifts (id INTEGER PRIMARY KEY AUTOINCREMENT, member_id NUMERIC, month NUMERIC, day NUMERIC, year NUMERIC, shift_length NUMERIC);
COMMIT;
