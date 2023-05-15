import React from 'react';

//add functionaltiy if token is expired(getting new token)
export function getHeaders() {
    let token = localStorage.getItem('token');
    token = 'Bearer ' + token;
     return {
        'Authorization' : token,
        'X-User-Role' : 'CUSTOMER'
     }
}