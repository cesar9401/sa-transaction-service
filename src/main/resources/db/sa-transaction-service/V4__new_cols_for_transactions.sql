
ALTER TABLE public.sa_transaction ADD COLUMN quantity INTEGER;
ALTER TABLE public.sa_transaction ADD COLUMN unit_price NUMERIC (19, 4);
ALTER TABLE public.sa_transaction ADD COLUMN unit_discount NUMERIC (19, 4);

-- update values
UPDATE public.sa_transaction SET quantity = 1;
UPDATE public.sa_transaction SET unit_price = transaction_total;
UPDATE public.sa_transaction SET unit_discount = discount_total;

-- set to null null
ALTER TABLE public.sa_transaction ALTER COLUMN quantity SET NOT NULL;
ALTER TABLE public.sa_transaction ALTER COLUMN unit_price SET NOT NULL;
ALTER TABLE public.sa_transaction ALTER COLUMN unit_discount SET NOT NULL;
