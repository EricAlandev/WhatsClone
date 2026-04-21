'use client'

import { UserType } from "@/typescript/types/UserType"

export const userService = async(data: UserType) => {
    const loginFetch = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/login/api`, {
        method: 'POST',
        headers: {
            'Content-type' : 'application/json'
        },
        body: JSON.stringify(data)
    })

    if(!loginFetch.ok){
        throw new Error("Server error" + loginFetch.status)
    }

    const response = await loginFetch.json();

    return response;
    
}