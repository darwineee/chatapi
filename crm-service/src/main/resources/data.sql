-- In production, a new client will be inserted manually,
-- and we have no data.sql because it contains API key
insert into clients (id, public_id, name, api_key)
values (0, 'MC-1234-5678', 'Microsoft Vietnam', '2cbb1a9e-b0a4-4050-92e5-05a9706102f5')
on conflict do nothing;