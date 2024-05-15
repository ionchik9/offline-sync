UPDATE estimates SET "discountType" = 'PERCENTAGE' WHERE "discountType" = 'Percentage';
UPDATE estimates SET "discountType" = 'AMOUNT' WHERE "discountType" = 'Amount';
UPDATE estimates SET "sentState" = 'DRAFT' WHERE "sentState" = 'Draft';
UPDATE estimates SET "sentState" = 'COMPLETED' WHERE "sentState" = 'Completed';
UPDATE estimates SET "sentState" = 'PRINTED' WHERE "sentState" = 'Printed';
UPDATE estimates SET "sentState" = 'SENT' WHERE "sentState" = 'Sent';
UPDATE estimates SET "sentState" = 'VIEWED' WHERE "sentState" = 'Viewed';
UPDATE estimates SET "taxType" = 'NONE' WHERE "taxType" = 'None';
UPDATE estimates SET "taxType" = 'PER_ITEM' WHERE "taxType" = 'Per_item';
UPDATE estimates SET "taxType" = 'ON_TOTAL' WHERE "taxType" = 'On_total';

UPDATE invoices SET "discountType" = 'PERCENTAGE' WHERE "discountType" = 'Percentage';
UPDATE invoices SET "discountType" = 'AMOUNT' WHERE "discountType" = 'Amount';
UPDATE invoices SET "sentState" = 'DRAFT' WHERE "sentState" = 'Draft';
UPDATE invoices SET "sentState" = 'COMPLETED' WHERE "sentState" = 'Completed';
UPDATE invoices SET "sentState" = 'PRINTED' WHERE "sentState" = 'Printed';
UPDATE invoices SET "sentState" = 'SENT' WHERE "sentState" = 'Sent';
UPDATE invoices SET "sentState" = 'VIEWED' WHERE "sentState" = 'Viewed';
UPDATE invoices SET "taxType" = 'NONE' WHERE "taxType" = 'None';
UPDATE invoices SET "taxType" = 'PER_ITEM' WHERE "taxType" = 'Per_item';
UPDATE invoices SET "taxType" = 'ON_TOTAL' WHERE "taxType" = 'On_total';
UPDATE invoices SET "dueType" = 'NONE' WHERE "dueType" = 'None';
UPDATE invoices SET "dueType" = 'ON_RECEIPT' WHERE "dueType" = 'On_receipt';
UPDATE invoices SET "dueType" = 'NEXT_DAY' WHERE "dueType" = 'Next_day';
UPDATE invoices SET "dueType" = 'DAYS_7' WHERE "dueType" = 'Days_7';
UPDATE invoices SET "dueType" = 'DAYS_30' WHERE "dueType" = 'Days_30';
UPDATE invoices SET "dueType" = 'CUSTOM_DATE' WHERE "dueType" = 'Custom_date';

UPDATE doc_histories SET type = 'CREATED' WHERE type = 'Created';
UPDATE doc_histories SET type = 'SENT' WHERE type = 'Sent';
UPDATE doc_histories SET type = 'OPENED' WHERE type = 'Opened';
UPDATE doc_histories SET type = 'OVERDUE' WHERE type = 'Overdue';
UPDATE doc_histories SET type = 'PRINTED' WHERE type = 'Printed';
UPDATE doc_histories SET type = 'PARTIALLY_PAID' WHERE type = 'Partially_paid';
UPDATE doc_histories SET type = 'OVERPAID' WHERE type = 'Overpaid';
UPDATE doc_histories SET type = 'FULLY_PAID' WHERE type = 'Fully_paid';
UPDATE doc_histories SET type = 'CLIENT_SIGNED' WHERE type = 'Client_signed';

UPDATE estimates_items SET "discountType" = 'PERCENTAGE' WHERE "discountType" = 'Percentage';
UPDATE estimates_items SET "discountType" = 'AMOUNT' WHERE "discountType" = 'Amount';

UPDATE invoices_items SET "discountType" = 'PERCENTAGE' WHERE "discountType" = 'Percentage';
UPDATE invoices_items SET "discountType" = 'AMOUNT' WHERE "discountType" = 'Amount';

UPDATE shares SET type = 'EMAIL' WHERE type = 'email';
UPDATE shares SET type = 'LINK' WHERE type = 'link';
UPDATE shares SET "sendStatus" = 'DRAFT' WHERE "sendStatus" = 'Draft';
UPDATE shares SET "sendStatus" = 'COMPLETED' WHERE "sendStatus" = 'Completed';
UPDATE shares SET "sendStatus" = 'PRINTED' WHERE "sendStatus" = 'Printed';
UPDATE shares SET "sendStatus" = 'SENT' WHERE "sendStatus" = 'Sent';
UPDATE shares SET "sendStatus" = 'VIEWED' WHERE "sendStatus" = 'Viewed';

UPDATE users SET source = 'ORIGINAL' WHERE source = 'original';
UPDATE users SET source = 'STRIPE' WHERE source = 'stripe';
UPDATE users SET source = 'MIGRATED_FROM_IM' WHERE source = 'migrated_from_old_im';
UPDATE users SET source = 'UPDATED_FROM_OLD_IM' WHERE source = 'updated_from_old_im';
UPDATE users SET "taxType" = 'NONE' WHERE "taxType" = 'None';
UPDATE users SET "taxType" = 'PER_ITEM' WHERE "taxType" = 'Per_item';
UPDATE users SET "taxType" = 'ON_TOTAL' WHERE "taxType" = 'On_total';