insert into clients (id, public_id, name, master_api_token)
values (0, 'MC-1234-5678', 'Microsoft Vietnam', '2cbb1a9e-b0a4-4050-92e5-05a9706102f5')
on conflict do nothing;