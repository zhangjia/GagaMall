
<div id="app">
    <el-cascader
            size="large"
            :options="options"
            v-model="selectedOptions"
            @change="handleChange">
    </el-cascader>
</div>
