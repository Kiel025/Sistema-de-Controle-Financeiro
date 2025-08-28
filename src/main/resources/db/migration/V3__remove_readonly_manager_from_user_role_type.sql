CREATE TYPE user_role_new AS ENUM ('ADMIN', 'USER');

ALTER TABLE app_user ALTER COLUMN role DROP DEFAULT;

ALTER TABLE app_user ALTER COLUMN role TYPE user_role_new USING role::text::user_role_new;

DROP TYPE user_role;

ALTER TYPE user_role_new RENAME TO user_role;

ALTER TABLE app_user ALTER COLUMN role SET DEFAULT 'USER';