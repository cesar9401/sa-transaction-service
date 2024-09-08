CREATE TABLE public.sa_category
(
    category_id        BIGINT  NOT NULL,
    parent_category_id BIGINT  NOT NULL,
    cat_description    VARCHAR NOT NULL,
    CONSTRAINT sa_category_pk PRIMARY KEY (category_id)
);

CREATE TABLE public.sa_sale
(
    sale_id                  UUID           NOT NULL,
    client_id                UUID           NOT NULL,
    entry_date               TIMESTAMP      NOT NULL,
    sum_total_transaction    NUMERIC(19, 4) NOT NULL,
    sum_total_discount       NUMERIC(19, 4) NOT NULL,
    sum_total_after_discount NUMERIC(19, 4) NOT NULL,
    cat_sale_status          BIGINT         NOT NULL,
    CONSTRAINT sa_sale_pk PRIMARY KEY (sale_id)
);


CREATE TABLE public.sa_transaction
(
    transaction_id       UUID           NOT NULL,
    sale_id              UUID           NOT NULL,
    entry_date           TIMESTAMP      NOT NULL,
    total_transaction    NUMERIC(19, 4) NOT NULL,
    total_discount       NUMERIC(19, 4) NOT NULL,
    total_after_discount NUMERIC(19, 4) NOT NULL,
    CONSTRAINT sa_transaction_pk PRIMARY KEY (transaction_id)
);


CREATE TABLE public.sa_reservation
(
    transaction_id         UUID      NOT NULL,
    room_id                UUID      NOT NULL,
    start_date             TIMESTAMP NOT NULL,
    end_date               TIMESTAMP NOT NULL,
    cat_reservation_status BIGINT    NOT NULL,
    CONSTRAINT sa_reservation_pk PRIMARY KEY (transaction_id)
);


CREATE TABLE public.sa_dish_order
(
    transaction_id       UUID         NOT NULL,
    dish_id              UUID         NOT NULL,
    food_ord_description VARCHAR(250) NOT NULL,
    CONSTRAINT sa_dish_order_pk PRIMARY KEY (transaction_id)
);


CREATE TABLE public.sa_payment
(
    payment_id     UUID           NOT NULL,
    sale_id        UUID           NOT NULL,
    payment_amount NUMERIC(19, 4) NOT NULL,
    entry_date     TIMESTAMP      NOT NULL,
    CONSTRAINT sa_payment_pk PRIMARY KEY (payment_id)
);


ALTER TABLE public.sa_category
    ADD CONSTRAINT sa_category_sa_category_fk
        FOREIGN KEY (parent_category_id)
            REFERENCES public.sa_category (category_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_payment
    ADD CONSTRAINT sale_sale_payment_fk
        FOREIGN KEY (sale_id)
            REFERENCES public.sa_sale (sale_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_transaction
    ADD CONSTRAINT sale_transaction_fk
        FOREIGN KEY (sale_id)
            REFERENCES public.sa_sale (sale_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_dish_order
    ADD CONSTRAINT transaction_dish_order_fk
        FOREIGN KEY (transaction_id)
            REFERENCES public.sa_transaction (transaction_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.sa_reservation
    ADD CONSTRAINT transaction_reservation_fk
        FOREIGN KEY (transaction_id)
            REFERENCES public.sa_transaction (transaction_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;