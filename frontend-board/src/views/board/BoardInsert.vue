<template>
  <div class="board">
    <div class="board__btn">
        <button class="board__button" v-on:click="fnSave">저장</button>
        <button class="board__button" v-on:click="fnList">목록</button>
    </div>
    <div class="board__contents">
      <table>
        <colgroup>
        <col style="width: 150px; background-color:#f7f8f9">
        </colgroup>
        <tbody>
          <tr>
            <th scope="row">제목</th>
            <td>
              <input type="text" v-model="title" class="board__input__title" placeholder="제목을 입력해주세요">
            </td>
          </tr>
          <div class="divider__row" style="margin: 10px 0"></div>
          <tr>
            <th scope="row">작성자</th>
            <td>
              <input type="text" v-model="author" class="board__input__author" placeholder="작성자를 입력해주세요" v-if="idx === undefined">
            </td>
          </tr>
          <div class="divider__row" style="margin: 10px 0"></div>
          <tr>
            <th scope="row">내용</th>
            <td>
              <textarea
                id=""
                cols="30"
                rows="10"
                v-model="contents"
                class="board__input__content"
                style="resize: none"
                placeholder="내용을 입력해주세요"
              ></textarea>
            </td>
          </tr>
        </tbody>
      </table>
        
    </div>
</div>
</template>
<script>
export default {
  name: 'BoardInsert',
  data() {
    return{
      requestBody: this.$route.query,
      idx: this.$route.query.idx,

      title: '',
      author: '',
      contents: '',
      create_at: ''
    }
  },
    mounted() {
      this.fnGetView();
    },
    methods: {
      fnGetView() {
        if(this.idx !== undefined) {
          this.$axios.get(this.$serverUrl + '/board/' + this.idx, {
            params: this.requestBody
          }).then((res)=> {
            this.title = res.data.title
            this.author = res.data.author
            this.contents = res.data.contents
            this.create_at = res.data.create_at
          }).catch((err)=> {
            console.log(err);
          })
        }
      },
      fnList() {
        delete this.requestBody.idx
        this.$router.push({
          path: './boardList',
          query: this.requestBody
        })
      },
      fnView(idx) { 
        this.requestBody.idx = idx 
        this.$router.push({ 
          path: './boardDetail',
          query: this.requestBody 
        }) 
      },
      fnSave() {
        if (this.title === '') {
          alert("제목을 입력해주세요.")
          return;
        } else if (this.author === '') {
          alert("작성자를 입력해주세요.")
          return;
        } else if (this.contents === ''){
          alert("내용을 입력해주세요.")
          return;
        }
        let apiUrl = this.$serverUrl + '/board'
        this.form = {
          "idx": this.idx,
          "title": this.title,
          "contents": this.contents,
          "author": this.author
        }

        if (this.idx === undefined) {
          //INSERT
          this.$axios.post(apiUrl, this.form)
          .then((res) => {
            console.log(
              "apiUrl:",  apiUrl, "form: ", this.form, this.idx);
            alert('글이 저장되었습니다.')
            this.fnView(res.data.idx)
            /** 목록에서 선택시 idx 존재 
             * * 저장시 idx undefined -> fnView에 res.data.idx
            인자 전달, 실행  
            */
          }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
            }
          })
        } else {
          //UPDATE
          this.$axios.patch(apiUrl, this.form)
          .then((res) => {
            alert('글이 저장되었습니다.')
            this.fnView(res.data.idx)
          }).catch((err) => {
            if (err.message.indexOf('Network Error') > -1) {
              alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      }
    }
  }
}


</script>
<style scoped>
input, textarea {
  margin-left: 15px;
}
</style>