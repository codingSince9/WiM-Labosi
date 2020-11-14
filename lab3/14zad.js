var dataBase = {
    verifyUser: async function (username, password) {
        console.log("U metodi verifyUser");
        return new Promise(function (resolve, reject) {
            reject(new Error("greška"));
        });
    },
    getRoles: async function (userInfo) {
        console.log("U metodi getRoles");
        return new Promise(function (resolve, reject) {
            resolve({
                rolename: "admin"
            });
        });
    },
    logAccess: async function (userInfo) {
        console.log("U metodi logAccess");
        return new Promise(function (resolve, reject) {
            resolve("Uspješno logirano");
        });
    }
};
const verifyUser = async function (username, password) {
    try {
        const userInfo = await dataBase.verifyUser(username, password);
        const rolesInfo = await dataBase.getRoles(userInfo);
        const logStatus = await dataBase.logAccess(userInfo);
        return userInfo;
    } catch (e) {
        return new Promise(function (resolve, reject) {
            reject(new Error("Greška prilikom autentifikacije"));
        });
    }
};
let result = verifyUser("user1", "pass1").then(function (result) {
    console.log("U then");
}).catch(function (result) {
    console.log("U catch");
});