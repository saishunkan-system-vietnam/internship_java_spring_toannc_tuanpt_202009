<template>
  <div>
      {{data.nameTour}}
    <v-list dense>
      <v-subheader><h4>Ten Giải</h4></v-subheader>
      <v-list-item-group v-model="item" color="primary">
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>
              <v-row v-b-popover.hover.right="'Kích vào để xem chi tiết'">
                <v-col>Tình trạng</v-col>
                <v-col>Team A&emsp;&emsp;&emsp;? - ?&emsp;&emsp;&emsp;TeamB</v-col>
                <v-col></v-col>
              </v-row>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </div>
</template>
<script>
export default {
  data() {
    return {
      data: "",
    };
  },
  created() {
    this.$store.dispatch("tournament/getByStatus", "0").then((response) => {
      this.data = response.data;
      console.log(this.data[0].nameTour);
    });
  },
  methods: {},
};
</script>
 