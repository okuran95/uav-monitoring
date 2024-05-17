CREATE TABLE IF NOT EXISTS public.uavs
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 0 MINVALUE 0 MAXVALUE 2000 CACHE 1 ),
    code character varying(30) COLLATE pg_catalog."default" NOT NULL,
    speed double precision NOT NULL,
    battery_percantage double precision NOT NULL,
    flight_started_datetime timestamp without time zone NOT NULL,
    position_id integer,
    flight_finished_datetime timestamp without time zone,
    CONSTRAINT uavs_pkey PRIMARY KEY (id)
)


CREATE TABLE IF NOT EXISTS public."position"
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 0 MINVALUE 0 MAXVALUE 2000 CACHE 1 ),
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    altitude double precision NOT NULL,
    CONSTRAINT position_pkey PRIMARY KEY (id)
)
