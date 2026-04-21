'use client'


    export const callConfigurationsListService = async() => {
        try{
            const call = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/configurations/options/api`, {
                method: "GET",
                headers: {
                    'Content-type' : 'application/json'
                }
            })

            if(!call.ok){
                throw new Error("fail in the service fetch");
            }

            const response = await call.json();

            return response;
        }

        catch(error){

        }
    }

