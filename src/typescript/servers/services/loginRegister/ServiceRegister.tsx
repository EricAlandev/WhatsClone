'use client'

import { UserType } from "@/typescript/types/UserType"

export const makeRegister = async(data: UserType) => {
    try{
        const registerFetch = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/register/api`, {
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
                    number: {
                        ddd: data?.ddd,
                        number: data?.number
                    },
                    nacionality: {
                        city: data?.city,
                        country: data?.country
                    }

                }
            )
        })
    }

    catch(error){

    }
}