INSERT INTO ticket_office_oauth2.roles (`id`, `name`) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN'), (3, 'ROLE_GUEST');

INSERT INTO ticket_office_oauth2.users (`id`, `login`, `name`, `password`) VALUES (1, 'roy', 'Roy', '$2y$10$kIlXr3np0JN8Ho3p47heM.pKFC5T9y.1fs6OISTqYwgTyKLQ2m5aO'), (2, 'craig', 'Craig', 'spring'), (3, 'greg', 'Greg', 'spring');

INSERT INTO ticket_office_oauth2.user_role (`user_id`, `role_id`) VALUES (1, 1), (2, 1), (3, 1), (1, 2);

INSERT INTO ticket_office_oauth2.oauth_client_details (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('clientapp', 'restservice', '123456', 'read,write', 'password,refresh_token', NULL, 'USER', NULL, NULL, NULL, NULL);