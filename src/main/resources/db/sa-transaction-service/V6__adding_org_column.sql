ALTER TABLE public.sa_sale ADD COLUMN organization_id UUID;

UPDATE public.sa_sale SET organization_id = '421cab63-3b9f-406b-8270-4e32070384d9';

ALTER TABLE public.sa_sale ALTER COLUMN organization_id SET NOT NULL;
