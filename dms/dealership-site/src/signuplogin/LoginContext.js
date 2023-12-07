import React, { createContext, useContext, useState } from "react";


const LoginContext = createContext({ type: "manager", user: "Foobar", id: "0" });

export function LoginProvider({ children }) {
	const [acct, setAcct] = useState(useContext(LoginContext));

	function updateAcct (newAcct) {
		setAcct(newAcct);
	}

	return (
		<LoginContext.Provider value={{ acct, updateAcct }}>
			{children}
		</LoginContext.Provider>
	);
}

export function useLoginData() {
	return useContext(LoginContext);
}
