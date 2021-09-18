-- Categories
INSERT INTO public.category (range, type) VALUES ('[0,10]', 'RANGE');
INSERT INTO public.category (range, type) VALUES ('(10,100]', 'RANGE');
INSERT INTO public.category (range, type) VALUES ('(100,)', 'RANGE');
INSERT INTO public.category (range, type) VALUES ('[0,1)', 'TX_DATA');
INSERT INTO public.category (range, type) VALUES ('[1,2)', 'TX_DATA');
INSERT INTO public.category (range, type) VALUES ('[2,3]', 'TX_DATA');
INSERT INTO public.category (range, type) VALUES ('[54,)', 'TX_DATA');

-- Tecnologies
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Zigbee Global', 2.0, 0.0, 2.4, '802.15.4-2011', 65527, 10.0, 0.25);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Classe 1', 1.0, 20.0, 2.4, '802.15.1', 8, 100.0, 3.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Classe 2', 1.0, 4.0, 2.4, '802.15.1', 8, 10.0, 2.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Bluetooth Classe 3', 1.0, 0.0, 2.4, '802.15.1', 8, 1.0, 1.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('BLE Classe 1', 2.0, 10.0, 2.4, NULL, 999999999, 50.0, 2.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('BLE Classe 1.5', 2.0, -20.0, 2.4, NULL, 999999999, 50.0, 1.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('BLE Classe 2', 2.0, -20.0, 2.4, NULL, 999999999, 50.0, 0.5);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('BLE Classe 3', 2.0, -20.0, 2.4, NULL, 999999999, 50.0, 0.125);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('WIFI 2.4Ghz', 22.0, 100.0, 2.4, '802.11b/g', 2007, 100.0, 54.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('WIFI 5Ghz', 160.0, 50.0, 5.0, '802.11ac', 2007, 100.0, 1024.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('LoraWAN', 0.125, 20.0, NULL, NULL, NULL, 15000.0, 0.25);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('GPRS', 0.2, 33.0, 1.0, NULL, NULL, 10000.0, 2.5);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('4G', 800, 45, 0.2, NULL, NULL, 10000.0, 300.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('WirelessHART', NULL, 10.0, 2.4, '802.15.4-2006', NULL, 200.0, 0.25);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Wibree', NULL, 10.0, 2.4, NULL, NULL, 10.0, 1.0);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Narrow Band', 0.18, 20.0, NULL, NULL, NULL, 20000.0, 0.127);
INSERT INTO public.technology (name_tec, bandwidth, consumption, frequency, ieee, nr_nodes, range_m, tx_data) VALUES('Sigfox', 0.001, 22.0, 0.9, NULL, NULL, 40000.0, 0.0006);