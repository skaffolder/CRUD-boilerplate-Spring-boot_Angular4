import { Pipe, PipeTransform } from '@angular/core';

/**
 * This pipe allows to filter a list of items
 * 
 * EXAMPLE:
 *  <div *ngFor="let item of list | search:{'field_1' : search1, 'field_2' : search2 }">
 *  </div>
 * 
 */
@Pipe({name:'search'})
export class SearchPipe implements PipeTransform{

    transform(list: any[], filter:any): any[] {
        // if list is null or empty
        if(!list || !list.length) return [];

        // map objects in filter
        Object.keys(filter).map(function(fieldName) {

            // get key to search
            let key:string = filter[fieldName];
            if(key) {

                // filter list for esch type of filter
                list = list.filter(item => {
                    
                    let field = item[fieldName];
                    
                    //filter string
                    if (typeof field == "string") {
                        return field.toLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
                    }

                    if(typeof field == "boolean") {
                        if(typeof key == "boolean")
                            return field == key;
                    }

                    if(typeof field == "number"){
                        return field.toString().toLowerCase().indexOf(key.toString().toLocaleLowerCase()) != -1;
                    }

                    // filter array
                    if (Array.isArray(field)) 
                    {
                        // find key in list
                        var found = field.filter( item => {
                            if (item.toLowerCase().indexOf(key.toLocaleLowerCase()) !== -1)
                                return item;
                        });

                        return found.length > 0;
                    }
                })
            }
        });

        return list;
        
    }

}