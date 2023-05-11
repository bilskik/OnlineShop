import React, { useMemo } from "react";
import useAuth from "./useAuth";

const useAuthHeaders = () => {
    const headers = useMemo(() => {
        let token = localStorage.getItem('token');
        token = "Bearer " + token;
        const headers = {
            'Authorization' : token,
            'X-User-Role' : 'CUSTOMER'
        };
        return headers;
    },[]);
}

export default useAuthHeaders;