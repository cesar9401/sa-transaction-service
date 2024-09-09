-- add column
ALTER TABLE public.sa_sale ADD COLUMN net_total_paid NUMERIC(19, 4);

-- update values
UPDATE public.sa_sale ss
SET net_total_paid = (
    SELECT SUM(sp.net_total)
    FROM public.sa_payment sp
    WHERE sp.sale_id = ss.sale_id
);

-- set to not null
ALTER TABLE public.sa_sale ALTER COLUMN net_total_paid SET NOT NULL;
