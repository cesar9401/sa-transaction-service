INSERT INTO public.sa_category (category_id, parent_category_id, cat_description) VALUES
(530, null, 'Payment_Method'),
(531, 530, 'PY_Cash'),
(532, 530, 'PY_Debit_or_Credit_Card');

ALTER TABLE public.sa_payment ADD COLUMN cat_payment_method BIGINT;

UPDATE public.sa_payment SET cat_payment_method = 531;

ALTER TABLE public.sa_payment ALTER COLUMN cat_payment_method SET NOT NULL;
