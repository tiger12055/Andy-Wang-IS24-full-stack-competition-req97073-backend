-- Insert product
INSERT INTO PRODUCT (product_number, product_name, scrum_master, product_owner, start_date, methodology)
VALUES (1, 'Product A', 'John Doe', 'Jane Smith', '2021-01-01', 'AGILE'), (2, 'Product B', 'John Doe', 'Peter Brown', '2021-03-15', 'WATERFALL'), (3, 'Product C', 'Bob Johnson', 'Alice Wang', '2021-05-01', 'AGILE'), (4, 'Product D', 'Emily Chen', 'David Kim', '2021-06-01', 'WATERFALL'), (5, 'Product E', 'Michael Lee', 'Amy Lee', '2021-07-01', 'AGILE'),(6, 'Product F', 'Michael Lee', 'Kevin Lee', '2021-08-01', 'WATERFALL'), (7, 'Product G', 'Richard Park', 'Karen Kim', '2021-09-01', 'WATERFALL'),(8, 'Product H', 'Steven Lee', 'Grace Kim', '2021-10-01', 'AGILE'),(9, 'Product I', 'Samantha Smith', 'Daniel Lee', '2021-11-01', 'WATERFALL'),
(10, 'Product J', 'John Doe', 'Olivia Lee', '2021-12-01', 'WATERFALL');

-- Insert developers
INSERT INTO DEVELOPER (id, developer_name)
VALUES (1, 'Dev A'),(2, 'Dev B'),(3, 'Dev C'),(4, 'Dev D'),(5, 'Dev E'),(6, 'Dev F'),(7, 'Dev G'),(8, 'Dev H'),(9, 'Dev I'),(10, 'Dev J');

-- Insert relationships into product_developer
INSERT INTO product_developer (product_number, developer_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (2, 6), (3,7), (4, 8), (4, 9), (4, 10), (5, 1),(5, 3),(5, 5),(5, 7), (6, 1), (6,3), (7 ,2), (8,3), (9,9), (10, 6);