
type ImageStatusMessage = {
    status: string
}

export default function ImageStatusMessage({status} : ImageStatusMessage){

    //define the logical of the image photo;
    let urlImage = "";
    switch(status){
        case"Visualized":
            urlImage = "/messages/Visualized.png"
            break
    
        case "not viewed":
            urlImage = "/messages/NonVisualized.png"
            break
    
        default:
            return null
    }

    return(
        <img
            src={urlImage}
            className=" max-h-[22px] "
        />
    )
}