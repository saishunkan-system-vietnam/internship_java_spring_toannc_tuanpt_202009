<template>
  <b-container fluid>
 
    <div class="text-center">
      <v-progress-circular
        v-if="loading == false"
        indeterminate
        :size="200"
        color="amber"
      ></v-progress-circular>
    </div>
    <v-card v-if="this.loading == true">
      <v-card-title>
        Tournamnet
        <v-spacer> </v-spacer>

        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      <v-btn class="mx-2" fab dark color="indigo" @click.stop="dialog = true">
        <v-icon dark> mdi-plus </v-icon>
      </v-btn>
      <v-data-table :headers="headers" :items="desserts" :search="search">
        <template v-slot:item="row">
          <tr>
            <td>{{ row.item.nameTour }}</td>
            <td>{{ row.item.title }}</td>
            <td>{{ row.item.type }}</td>
            <td>{{ row.item.timeStart }}</td>
            <td>{{ row.item.timeEnd }}</td>
            <td v-if="row.item.status == 0" style="color: Blue">not</td>
            <td v-if="row.item.status == 1" style="color: red">doing</td>
            <td v-if="row.item.status == 2" style="color: Gray">end</td>
            <td>
              <router-link
                :to="{ path: '/DetailTournament/' + row.item.idTour }"
              >
                <v-btn fab small>
                  <v-icon>mdi-forward</v-icon>
                </v-btn>
              </router-link>
              
              <v-btn
                v-if="row.item.status == 0"
                class="mx-2"
                fab
                small
                @click="EditTour(row.item)"
              >
                <v-icon dark>mdi-pencil</v-icon>
              </v-btn>
              <v-btn
                v-if="row.item.status == 0"
                class="mx-2"
                fab
                small
                @click="deleteTour(row.item)"
              >
                <v-icon dark>mdi-delete</v-icon>
              </v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>
      <b-modal id="modal-1" title="Delete" @ok="handleOk()">
        <p class="my-4">Are you sure!</p>
      </b-modal>

      <v-dialog v-model="dialog" max-width="1000px" persistent>
        <v-card>
          <v-card-title class="headline"> Add </v-card-title>

          <v-card-text>
            <AddTournament
              :callback="hideModal"
              :getList="getListTournament"
            ></AddTournament>
          </v-card-text>
        </v-card>
      </v-dialog>
          <v-dialog v-model="dialogEdit" max-width="1000px" persistent>
        <v-card>
          <v-card-title class="headline"> Add </v-card-title>

          <v-card-text>
            <EditTournament
              :callback="hideModalEdit"
              :getList="getListTournament"
              :editData="edit"
            ></EditTournament>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-card>
  </b-container>
</template>
<script>
import AddTournament from "./AddTournament";
import EditTournament from "./EditTournament";
export default {
  components: {
    AddTournament,
    EditTournament
  },
  data() {
    return {
      loading: false,
      index: "",
      search: "",
      idDelete: "",
      dialog: false,
      dialogEdit: false,
      edit: {},
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "nameTour",
        },
        { text: "Title", value: "title" },
        { text: "Type", value: "type" },
        { text: "Time Start", value: "timeStart" },
        { text: "Time End", value: "timeEnd" },
        { text: "status", value: "status" },
        { text: "action", sortable: false },
      ],
      desserts: [],
    };
  },
  methods: {
    getListTournament() {
      this.$store.dispatch("tournament/getAll").then((response) => {
        this.desserts = response.data;
      });
    },
    EditTour(id) {
      this.edit = id;
      console.log(this.edit)
      this.dialogEdit = true;
      
    },
    hideModalEdit() {
      console.log("a");
      this.dialogEdit = false;
    },
    hideModal() {
      this.dialog = false;
    },
    deleteTour(id) {
      this.$bvModal.show("modal-1");
      this.index = this.desserts.indexOf(id);
      this.idDelete = id.idTour;
    },
    handleOk() {
      this.$store
        .dispatch("tournament/delete", this.idDelete)
        .then((response) => {
          alert(response.data);
          this.desserts.splice(this.index);
          this.getListTournament();
        });
    },
  },
  created() {
    this.$store.dispatch("tournament/updateStatus").then((response) => {
      this.loading = true;
      this.getListTournament();
    });
  },
};
</script>
