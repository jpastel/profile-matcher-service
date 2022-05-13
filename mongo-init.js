db.createUser(
    {
        user: "admin",
        pwd: "@dm1N",
        roles: [
            {
                role: "readWrite",
                db: "playerprofile"
            }
        ]
    }
);