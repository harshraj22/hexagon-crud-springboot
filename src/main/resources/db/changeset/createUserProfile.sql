CREATE TABLE user_profile (
    id SERIAL PRIMARY KEY,
    username VARCHAR(40) UNIQUE NOT NULL,
    name VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL,
    age INTEGER NOT NULL,
    FOREIGN KEY (username) REFERENCES auth_creds(username)
);
