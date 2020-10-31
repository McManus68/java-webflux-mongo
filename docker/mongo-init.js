db.createUser(
        {
            user: "manu",
            pwd: "manu",
            roles: [
                {
                    role: "readWrite",
                    db: "webflux"
                }
            ]
        }
);