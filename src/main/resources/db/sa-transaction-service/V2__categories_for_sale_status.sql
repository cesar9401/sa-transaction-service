ALTER TABLE public.sa_category ALTER COLUMN parent_category_id DROP NOT NULL;

INSERT INTO public.sa_category (category_id, parent_category_id, cat_description) VALUES
(520, null, 'Sale_Status'),
(521, 520, 'SS_Completed'),
(522, 520, 'SS_Canceled'),
(523, 520, 'SS_Pending_Payment');
