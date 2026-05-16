'use client'

import { UserType } from "@/typescript/types/UserType"

export const makeRegister = async(data: UserType) => {
    try{
        const request = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/api/users`, {
            method: 'POST',
            headers: {
                'Content-type' : 'application/json'
            },
            body: JSON.stringify(
                {
                    name: data?.name,
                    email: data?.email,
                    password: data?.password,
                    birthday: data?.birthday,
                    ddd: data?.ddd,
                    number: data?.number,

                    city: data?.city,
                    country: data?.country

                }
            )
        })

        if(!request.ok){
            throw new Error("Server error" + request.status)
        }

        const response = await request.json();

        return response
    }

    catch(error){

    }
}