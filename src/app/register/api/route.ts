import { NextResponse } from "next/server";


export async function POST(req: Request){

    try{
        const body = await req.json();

        console.log("before the request");
        const request = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/api/users`, {
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

        return new Response(JSON.stringify(response), {
            status: 200, 
            headers: {
                'Content-type' : 'application/json'
            }
        })

    }

    catch(error: any){
        return new Response(JSON.stringify(error?.message), {
            status: 400, 
            headers: {
                'Content-type' : 'application/json'
            }
        })
    }

}