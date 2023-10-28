CREATE TABLE user_profile (
    id SERIAL PRIMARY KEY,
    username VARCHAR(40) UNIQUE NOT NULL,
    name VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL,
    -- age must be a positive integer, add the constraint
    age INTEGER NOT NULL CHECK (age > 0),
    FOREIGN KEY (username) REFERENCES auth_creds(username)
);
