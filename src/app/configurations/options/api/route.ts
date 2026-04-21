

export async function GET(){
    try{
        const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL2}/options`, {
            method: "GET",
            headers: {
                'Content-type' : 'application/json'
            }
        });

        if(!call.ok){
            throw new Error("fail in the service fetch");
        };

        const response = await call.json();

        return new Response(JSON.stringify(response), {
            status: 200,
            headers: {
                'Content-type' : 'application/json'
            }
        })
    }

    catch(error: any){
        return new Response(JSON.stringify(error?.message), {
            status: 200,
            headers: {
                'Content-type' : 'application/json'
            }
        })
    }
}