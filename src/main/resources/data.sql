INSERT INTO client (id, email) VALUES
    (1, 'a@gmail.com'),
    (2, 'b@gmail.com'),
    (3, 'c@gmail.com');

INSERT INTO feature (id, name) VALUES
    (1, 'feature1'),
    (2, 'feature2'),
    (3, 'feature3');

INSERT INTO client_features (client_id, feature_id) VALUES
    (1, 2),
    (2, 3),
    (3, 1);