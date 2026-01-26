-- liquibase formatted sql

-- changeset siarhey.pavirayeu:001-1
-- comment: Creating the users table
CREATE TABLE users (
                       id UUID NOT NULL PRIMARY KEY,
                       first_name VARCHAR(20)
);

-- changeset siarhey.pavirayeu:001-2
-- comment: Create a unique index for users.id
CREATE UNIQUE INDEX IF NOT EXISTS users_id_uindex ON users(id);

-- changeset siarhey.pavirayeu:001-3
-- comment: Creating the review table
CREATE TABLE review (
                        id UUID NOT NULL PRIMARY KEY,
                        text VARCHAR(200) NOT NULL,
                        rating INTEGER NOT NULL,
                        location VARCHAR(50) NOT NULL,
                        created TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
                        updated TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
                        user_id UUID NOT NULL
);

-- changeset siarhey.pavirayeu:001-4
-- comment: Adding a foreign key for review.user_id
ALTER TABLE review
    ADD CONSTRAINT review_users_id_fk
        FOREIGN KEY (user_id) REFERENCES users(id)
            ON UPDATE CASCADE ON DELETE CASCADE;