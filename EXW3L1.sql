PGDMP     :    	                {           EXW3L1 #   14.7 (Ubuntu 14.7-0ubuntu0.22.04.1)    15.2 *    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16389    EXW3L1    DATABASE     t   CREATE DATABASE "EXW3L1" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE "EXW3L1";
                admin    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4            �            1259    16391    clienti    TABLE     �   CREATE TABLE public.clienti (
    id_cliente integer NOT NULL,
    nome character varying NOT NULL,
    cognome character varying NOT NULL,
    datadinascita date NOT NULL,
    regioneresidenza character varying NOT NULL
);
    DROP TABLE public.clienti;
       public         heap    postgres    false    4            �            1259    16396    clienti_NumeroCliente_seq    SEQUENCE     �   CREATE SEQUENCE public."clienti_NumeroCliente_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public."clienti_NumeroCliente_seq";
       public          postgres    false    4    209            �           0    0    clienti_NumeroCliente_seq    SEQUENCE OWNED BY     V   ALTER SEQUENCE public."clienti_NumeroCliente_seq" OWNED BY public.clienti.id_cliente;
          public          postgres    false    210            �            1259    16397    fatture    TABLE     F  CREATE TABLE public.fatture (
    num_fattura integer NOT NULL,
    tipologia character varying NOT NULL,
    importo double precision DEFAULT '0'::double precision NOT NULL,
    iva double precision DEFAULT '0.22'::double precision NOT NULL,
    id_cliente integer,
    datafattura date NOT NULL,
    id_fornitore integer
);
    DROP TABLE public.fatture;
       public         heap    postgres    false    4            �            1259    16404    fatture_IdCliente_seq    SEQUENCE     �   CREATE SEQUENCE public."fatture_IdCliente_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public."fatture_IdCliente_seq";
       public          postgres    false    211    4            �           0    0    fatture_IdCliente_seq    SEQUENCE OWNED BY     R   ALTER SEQUENCE public."fatture_IdCliente_seq" OWNED BY public.fatture.id_cliente;
          public          postgres    false    212            �            1259    16405    fatture_NumeroFattura_seq    SEQUENCE     �   CREATE SEQUENCE public."fatture_NumeroFattura_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public."fatture_NumeroFattura_seq";
       public          postgres    false    211    4            �           0    0    fatture_NumeroFattura_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public."fatture_NumeroFattura_seq" OWNED BY public.fatture.num_fattura;
          public          postgres    false    213            �            1259    16406    fatture_NumeroFornitore_seq    SEQUENCE     �   CREATE SEQUENCE public."fatture_NumeroFornitore_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public."fatture_NumeroFornitore_seq";
       public          postgres    false    211    4            �           0    0    fatture_NumeroFornitore_seq    SEQUENCE OWNED BY     Z   ALTER SEQUENCE public."fatture_NumeroFornitore_seq" OWNED BY public.fatture.id_fornitore;
          public          postgres    false    214            �            1259    16407 	   fornitori    TABLE     �   CREATE TABLE public.fornitori (
    id_fornitore integer NOT NULL,
    denominazione character varying NOT NULL,
    regioneresidenza character varying NOT NULL
);
    DROP TABLE public.fornitori;
       public         heap    postgres    false    4            �            1259    16412    fornitori_NumeroFornitore_seq    SEQUENCE     �   CREATE SEQUENCE public."fornitori_NumeroFornitore_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public."fornitori_NumeroFornitore_seq";
       public          postgres    false    4    215            �           0    0    fornitori_NumeroFornitore_seq    SEQUENCE OWNED BY     ^   ALTER SEQUENCE public."fornitori_NumeroFornitore_seq" OWNED BY public.fornitori.id_fornitore;
          public          postgres    false    216            �            1259    16413    prodotti    TABLE     �   CREATE TABLE public.prodotti (
    id_prod integer NOT NULL,
    descrizione character varying NOT NULL,
    in_prod boolean DEFAULT true NOT NULL,
    in_comm boolean DEFAULT true NOT NULL,
    data_attiv date NOT NULL,
    data_disattiv date
);
    DROP TABLE public.prodotti;
       public         heap    postgres    false    4            �            1259    16420    prodotti_IdProdotto_seq    SEQUENCE     �   CREATE SEQUENCE public."prodotti_IdProdotto_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public."prodotti_IdProdotto_seq";
       public          postgres    false    4    217            �           0    0    prodotti_IdProdotto_seq    SEQUENCE OWNED BY     R   ALTER SEQUENCE public."prodotti_IdProdotto_seq" OWNED BY public.prodotti.id_prod;
          public          postgres    false    218            �           2604    16421    clienti id_cliente    DEFAULT     }   ALTER TABLE ONLY public.clienti ALTER COLUMN id_cliente SET DEFAULT nextval('public."clienti_NumeroCliente_seq"'::regclass);
 A   ALTER TABLE public.clienti ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    210    209            �           2604    16422    fatture num_fattura    DEFAULT     ~   ALTER TABLE ONLY public.fatture ALTER COLUMN num_fattura SET DEFAULT nextval('public."fatture_NumeroFattura_seq"'::regclass);
 B   ALTER TABLE public.fatture ALTER COLUMN num_fattura DROP DEFAULT;
       public          postgres    false    213    211                        2604    16425    fornitori id_fornitore    DEFAULT     �   ALTER TABLE ONLY public.fornitori ALTER COLUMN id_fornitore SET DEFAULT nextval('public."fornitori_NumeroFornitore_seq"'::regclass);
 E   ALTER TABLE public.fornitori ALTER COLUMN id_fornitore DROP DEFAULT;
       public          postgres    false    216    215                       2604    16426    prodotti id_prod    DEFAULT     y   ALTER TABLE ONLY public.prodotti ALTER COLUMN id_prod SET DEFAULT nextval('public."prodotti_IdProdotto_seq"'::regclass);
 ?   ALTER TABLE public.prodotti ALTER COLUMN id_prod DROP DEFAULT;
       public          postgres    false    218    217            �          0    16391    clienti 
   TABLE DATA           ]   COPY public.clienti (id_cliente, nome, cognome, datadinascita, regioneresidenza) FROM stdin;
    public          postgres    false    209   0       �          0    16397    fatture 
   TABLE DATA           n   COPY public.fatture (num_fattura, tipologia, importo, iva, id_cliente, datafattura, id_fornitore) FROM stdin;
    public          postgres    false    211   �0       �          0    16407 	   fornitori 
   TABLE DATA           R   COPY public.fornitori (id_fornitore, denominazione, regioneresidenza) FROM stdin;
    public          postgres    false    215   61       �          0    16413    prodotti 
   TABLE DATA           e   COPY public.prodotti (id_prod, descrizione, in_prod, in_comm, data_attiv, data_disattiv) FROM stdin;
    public          postgres    false    217   �1       �           0    0    clienti_NumeroCliente_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public."clienti_NumeroCliente_seq"', 22, true);
          public          postgres    false    210            �           0    0    fatture_IdCliente_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public."fatture_IdCliente_seq"', 2, true);
          public          postgres    false    212            �           0    0    fatture_NumeroFattura_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public."fatture_NumeroFattura_seq"', 9, true);
          public          postgres    false    213            �           0    0    fatture_NumeroFornitore_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public."fatture_NumeroFornitore_seq"', 1, true);
          public          postgres    false    214            �           0    0    fornitori_NumeroFornitore_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public."fornitori_NumeroFornitore_seq"', 11, true);
          public          postgres    false    216            �           0    0    prodotti_IdProdotto_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public."prodotti_IdProdotto_seq"', 15, true);
          public          postgres    false    218                       2606    16428    clienti clienti_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.clienti
    ADD CONSTRAINT clienti_pkey PRIMARY KEY (id_cliente);
 >   ALTER TABLE ONLY public.clienti DROP CONSTRAINT clienti_pkey;
       public            postgres    false    209                       2606    16430    fatture fatture_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.fatture
    ADD CONSTRAINT fatture_pkey PRIMARY KEY (num_fattura);
 >   ALTER TABLE ONLY public.fatture DROP CONSTRAINT fatture_pkey;
       public            postgres    false    211            	           2606    16432    fornitori fornitori_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.fornitori
    ADD CONSTRAINT fornitori_pkey PRIMARY KEY (id_fornitore);
 B   ALTER TABLE ONLY public.fornitori DROP CONSTRAINT fornitori_pkey;
       public            postgres    false    215                       2606    16434    prodotti prodotti_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.prodotti
    ADD CONSTRAINT prodotti_pkey PRIMARY KEY (id_prod);
 @   ALTER TABLE ONLY public.prodotti DROP CONSTRAINT prodotti_pkey;
       public            postgres    false    217                       2606    16435    fatture FK_fatture_clienti    FK CONSTRAINT     �   ALTER TABLE ONLY public.fatture
    ADD CONSTRAINT "FK_fatture_clienti" FOREIGN KEY (id_cliente) REFERENCES public.clienti(id_cliente);
 F   ALTER TABLE ONLY public.fatture DROP CONSTRAINT "FK_fatture_clienti";
       public          postgres    false    211    3845    209                       2606    16440    fatture FK_fatture_fornitori    FK CONSTRAINT     �   ALTER TABLE ONLY public.fatture
    ADD CONSTRAINT "FK_fatture_fornitori" FOREIGN KEY (id_fornitore) REFERENCES public.fornitori(id_fornitore);
 H   ALTER TABLE ONLY public.fatture DROP CONSTRAINT "FK_fatture_fornitori";
       public          postgres    false    3849    215    211            �   �   x�M̽�0�����􇨌���d��r,5�����\�����k�p*#�{:&7]�TV�Z�8	�C�e�'44>(g^���4\)�L�B����1Sy#%�V��i�9:���8e��z�Ď�̌A����j+�Zܸ��F��b9�      �   s   x�m��� ��s�Nx1"G-`+��:6���?�2dB��ڇ �$�P���~��\���41�yV_Y��������c��J�;�c�L�M.�:+��3���f��)      �   [   x��1
�0 �9y�P����Ppr	5J &%v����v	F�Ox�!�`��c��ُ�b�������Jq[I�L	�Ve(�rx�n"�\� O      �   x   x�M�1� ����X�i\۵�B�,7(ɠ�п_m�t;�}>��S|� �9��h�|O���\���+
����3WN�!R�z�d]�����i���H�mm��ˋ��%�vڵ�^���%�     