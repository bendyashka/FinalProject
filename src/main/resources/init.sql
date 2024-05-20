CREATE TABLE GymMembers (
                            member_id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50),
                            last_name VARCHAR(50),
                            subscription_expiry_date VARCHAR(150),
                            is_subscription_active BOOLEAN,
                            group_number INT
);