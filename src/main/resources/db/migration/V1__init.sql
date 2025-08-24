create extension if not exists "uuid-ossp";

create table app_user (
    id uuid primary key default uuid_generate_v4(),
    name varchar(120) not null,
    email varchar(160) not null unique,
    password_hash varchar(255) not null,
    created_at timestamp not null default now()
);

create table category (
    id uuid primary key default uuid_generate_v4(),
    name varchar(80) not null,
    user_id uuid not null references app_user(id) on delete cascade,
    created_at timestamp not null default now(),
    unique (user_id, name)
);

create type launc_type as enum ('RECEITA', 'DESPESA');

create table launch(
    id uuid primary key default uuid_generate_v4(),
    description varchar(255) not null,
    amount numeric(19, 2) not null,
    date date not null,
    type launc_type not null,
    category_id uuid references category(id) on delete set null,
    user_id uuid not null references app_user(id) on delete cascade,
    created_at timestamp not null default now()
);

create index idx_launc_user_date on launch(user_id, date);
create index idx_launc_user_type on launch(user_id, type);