import { NextResponse } from "next/server";


export async function POST(req: Request){

    try{
        const body = await req.json();
        const request = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/api/login`, {
            method: 'POST',
            headers: {
                'Content-type' : 'application/json'
            },
            body: JSON.stringify(
                body
            )
        })

        if(!request.ok){
            throw new Error("Server error" + request.status)
        }

        const response = await request.json();

        return response;

    }

    catch(error){
        return NextResponse.json({ error: "Internal Server Error" }, { status: 400 });
    }

}