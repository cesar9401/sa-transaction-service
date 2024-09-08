-- public.sa_transaction
ALTER TABLE public.sa_transaction RENAME total_transaction TO transaction_total;
ALTER TABLE public.sa_transaction RENAME total_discount TO discount_total;
ALTER TABLE public.sa_transaction RENAME total_after_discount TO net_total;

-- public.sa_sale
ALTER TABLE public.sa_sale RENAME sum_total_transaction TO total_transaction_sum;
ALTER TABLE public.sa_sale RENAME sum_total_discount TO total_discount_sum;
ALTER TABLE public.sa_sale RENAME sum_total_after_discount TO net_total_for_transactions;

-- public.sa_payment
ALTER TABLE public.sa_payment RENAME payment_amount TO net_total;
