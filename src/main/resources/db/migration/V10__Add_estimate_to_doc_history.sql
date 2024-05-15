alter table doc_histories
    add "estimateId" uuid;

ALTER TABLE doc_histories
    ADD CONSTRAINT "FK_8f12deBc1427d374aca8b5d472F" FOREIGN KEY ("estimateId") REFERENCES estimates (id)
     ON DELETE CASCADE;