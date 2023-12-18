class Animals {
    constructor(name, arr = []) {
        this.name = name;
        this.array = arr;
    }

    getArray() {
        return this.array;
    }

    getName() {
        return this.name;
    }


}


function setValue() {
    let list = new Map();
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("good")
        }
    };
    xhttp.open("POST", 'rerp', true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send();

    xhttp.onload = () => {
        if (xhttp.status == 200) {
            let ret = xhttp.responseText.replaceAll(' , ', ',');
            ret = ret.replaceAll('\n', '');
            ret = ret.replaceAll('[', '');
            ret = ret.replaceAll(']', '');
            console.log(ret);
            let arr = ret.split(',');
            arr.forEach(el => {
                let tmp = el.split(' ');
                let key = tmp[0];
                list.set(key, tmp.slice(1));
            });
        } else {
            console.log('ERROr')
            console.log(xhttp.responseText);
            console.log(xhttp.response);
        }
        paintList(list)
        let lt = document.getElementsByClassName('list');
        for (let i = 0; i < lt.length; i++) {
            lt[i].addEventListener('click', function () {
                console.log(this.parentNode.children[2].children);
                pack = this.parentNode.children[2].children
                for (const child of pack) {
                    if (child.className == 'mv') {
                        if (child.style.display == "block") {
                            child.style.display = "none";
                            child.parentNode.parentNode.children[1].textContent = '[+]';
                        } else {
                            child.style.display = "block";
                            child.parentNode.parentNode.children[1].textContent = '[-]';
                        }
                    }
                }

            });
        }
    }






    // bird = ["coroka, popugay, vorona"];
    // list.push(new Animals("Bird", bird));
    // . . .
    return list
}

function paintList(nt) {
    console.log(nt)
    let bird = ["coroka", "popugay", "vorona"];
    let clss = ["bird", "people", "animal"];

    let body = document.getElementById('bd');
    let mainDiv = document.createElement('div');
    mainDiv.id = 'block';
    let ol = document.createElement('ol');
    for (const [key, value] of nt) {


        let li = document.createElement('li');
        let nm = document.createElement('a');
        nm.innerHTML = key;
        li.appendChild(nm);

        let btn = document.createElement('button');
        btn.className = 'list';
        btn.innerHTML = '[+]';
        li.appendChild(btn);

        let ul = document.createElement('ul');
        value.forEach(elem => {
            let inLi = document.createElement('li');
            inLi.className = 'mv';
            inLi.style.display = 'none';
            inLi.innerHTML = elem;
            ul.appendChild(inLi);
        });
        li.appendChild(ul);
        ol.appendChild(li);
    };

    mainDiv.appendChild(ol);
    body.appendChild(mainDiv);
}

function disp(elem) {
    let ls = elem
    console.log(elem)
    console.log('click');
    // if (ls.style.display == "block") {
    //     ls.style.display = "none";
    // } else {
    //     ls.style.display = "block";
    // }
}



let notes = setValue();
// paintList(notes);
// let lt = document.getElementsByClassName('list');
// for (let i = 0; i < lt.length; i++) {
//     lt[i].addEventListener('click', function () {
//         console.log(this.parentNode.children[2].children);
//         pack = this.parentNode.children[2].children
//         for (const child of pack) {
//             if (child.className == 'mv') {
//                 if (child.style.display == "block") {
//                     child.style.display = "none";
//                 } else {
//                     child.style.display = "block";
//                 }
//             }
//         }

//     });
// }
