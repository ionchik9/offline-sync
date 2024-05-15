ALTER TABLE users
    ADD COLUMN "ssoUserId" BIGINT NULL;

CREATE UNIQUE INDEX users_sso_user_id_unique_idx ON users ("ssoUserId");