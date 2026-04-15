'use client'

import { UserType } from "@/typescript/types/UserType"

export const makeLogin = async(data: UserType) => {
    const loginFetch = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/login/api`, {
        method: 'POST',
        headers: {
            'Content-type' : 'application/json'
        },
        body: JSON.stringify({
            data
        })
    })
}