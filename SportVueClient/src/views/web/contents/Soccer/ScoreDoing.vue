<template>
  <div>

  </div>
</template>
<script>
export default {
  data() {
    return {
        data:'',
    };
  },
  created() {
    this.$store.dispatch("tournament/getByStatus",'1').then((response) => {
      this.data = response.data;
      console.log(this.data)
    });
  },
  methods: {},
};
</script>
 