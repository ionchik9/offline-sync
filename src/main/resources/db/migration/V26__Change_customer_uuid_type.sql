alter table users
    alter column "customerUid" type uuid using "customerUid"::uuid;