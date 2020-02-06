var vue = new Vue({
    el:"#app",
    data:{
        flag:true,
        btn:true,
        labelPosition: 'right',
        students: [],
        search: '',
        currentPage:1,
        pageSize:10,
        totalPage:0,
        student: {},
    },
    methods:{
        handleEdit(index, row) {
            console.log(index, row);
            this.student = row;
            this.flag= false;
            this.btn = false;
        },
        handleDelete(index, sid) {
            console.log(index, sid);
            var params = new URLSearchParams();
            params.append("sid",sid);
            axios.post("http://localhost:8686/stu/del",params).then((res)=>{
                if (res.data.code == '2001') {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.students.splice(index,1)
                }
                if (res.data.code == '2002') {
                    this.$message.error('删除失败');
                }
            })
        },
        handleSizeChange: function (size) {
            this.pageSize = size;
            console.log(this.pageSize)  //每页下拉显示数据
            this.init();
        },
        handleCurrentChange: function(currentPage){
            this.currentPage = currentPage;
            console.log(this.currentPage)  //点击第几页
            this.init();
        },
        init: function () {
            var params = new URLSearchParams();
            params.append("currentPage",this.currentPage);
            params.append("pageSize",this.pageSize);
            axios.post("http://localhost:8686/stu/getAll",params,{
                headers:{
                    Authorization:"masao",
                    type:"1"
                }
            }).then((res)=>{
                if (res.data.code == '2002') {
                    this.$message.error('请求服务器失败');
                    return;
                }
                var map = JSON.parse(res.data.data);
                this.students = map.students;
                this.totalPage = map.total;
            })
        },
        updateStu: function () {
            axios.post("http://localhost:8686/stu/update",this.student).then((res)=>{
                if (res.data.code == '2001') {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                }
                if (res.data.code == '2002') {
                    this.$message.error('修改失败');
                }
            })
        },
        addBtn: function () {
            this.student={};
            this.flag=false;
            this.btn = true;
        },
        addStu: function () {
            if (this.student.sname == null || this.student.sname =='') {
                this.$message.error('请填写学员姓名');
                return;
            }
            if (this.student.sage == null || this.student.sage =='') {
                this.$message.error('请填写学员年龄');
                return;
            }
            axios.post("http://localhost:8701/add",this.student).then((res)=>{
                if (res.data.code == '2001') {
                    this.$message({
                        message: '添加成功',
                        type: 'success'
                    });
                }
                if (res.data.code == '2002') {
                    this.$message.error('添加失败');
                }
            })
        },
        back: function () {
            this.flag = true;
            this.init();
        }
    },
    created() {
        this.init();
    },
    mounted(){

    }
})